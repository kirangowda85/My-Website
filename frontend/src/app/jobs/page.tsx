import React from 'react';
import { Button } from '@/components/ui/Button';

export default function JobsListing() {
  const jobs = [
    { id: 1, title: 'Event Helper - Food Festival', employer: 'Downtown Events LLC', location: 'Downtown Expo Center', date: 'Jun 20', pay: 'Rs 1200/shift', type: 'Urgent' },
    { id: 2, title: 'Retail Inventory Assistant', employer: 'Westside Mall', location: 'Westside Mall', date: 'Jun 24', pay: 'Rs 900/shift', type: 'Verified' },
    { id: 3, title: 'Catering Staff', employer: 'Gourmet Bites', location: 'Grand Hotel', date: 'Jun 25', pay: 'Rs 1500/shift', type: 'Verified' },
  ];

  return (
    <div className="mx-auto max-w-5xl py-8">
      <div className="mb-8 flex flex-col items-start justify-between gap-4 md:flex-row md:items-center">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Available Jobs</h1>
          <p className="mt-1 text-gray-600">Find and apply to temporary shifts near you.</p>
        </div>
        <div className="flex w-full gap-2 md:w-auto">
          <input
            type="text"
            placeholder="Search jobs"
            className="w-full rounded-lg border border-gray-300 px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 md:w-64"
          />
          <Button variant="secondary">Filter</Button>
        </div>
      </div>

      <div className="flex flex-col gap-4">
        {jobs.map((job) => (
          <div key={job.id} className="flex flex-col items-start justify-between gap-4 rounded-lg border border-gray-100 bg-white p-6 shadow-sm transition-shadow hover:shadow-md sm:flex-row sm:items-center">
            <div className="flex-grow">
              <div className="mb-1 flex flex-wrap items-center gap-2">
                <h2 className="text-xl font-semibold text-gray-900">{job.title}</h2>
                {job.type === 'Urgent' && <span className="rounded-full bg-red-100 px-2 py-0.5 text-xs font-medium text-red-800">Urgent</span>}
                {job.type === 'Verified' && <span className="rounded-full bg-blue-100 px-2 py-0.5 text-xs font-medium text-blue-800">Verified Employer</span>}
              </div>
              <p className="mb-2 text-sm text-gray-500">{job.employer}</p>
              <div className="flex flex-wrap gap-4 text-sm text-gray-600">
                <div>Location: {job.location}</div>
                <div>Date: {job.date}</div>
                <div className="font-medium text-green-700">Pay: {job.pay}</div>
              </div>
            </div>
            <div className="w-full sm:w-auto">
              <Button fullWidth>Apply Now</Button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
