import React from 'react';
import Link from 'next/link';
import { Button } from '@/components/ui/Button';

export default function Home() {
  return (
    <div className="flex min-h-[calc(100vh-4rem)] flex-col">
      <section className="-mx-4 bg-hero px-4 py-20 text-white sm:-mx-6 sm:px-6 lg:-mx-8 lg:px-8 lg:py-28">
        <div className="mx-auto max-w-4xl text-center">
          <h1 className="mb-6 text-4xl font-extrabold tracking-normal sm:text-5xl lg:text-6xl">
            WorkShift
          </h1>
          <p className="mx-auto mb-10 max-w-2xl text-lg text-blue-100 sm:text-xl">
            Find verified temporary workers for real shifts, or apply to trusted part-time jobs near you.
          </p>
          <div className="flex flex-col items-center justify-center gap-4 sm:flex-row">
            <Link href="/register">
              <Button size="lg" className="w-full border-transparent bg-white text-blue-600 shadow-lg hover:bg-gray-50 sm:w-auto">
                I want to work
              </Button>
            </Link>
            <Link href="/register">
              <Button variant="outline" size="lg" className="w-full border-white text-white hover:bg-white/10 sm:w-auto">
                I need to hire
              </Button>
            </Link>
          </div>
        </div>
      </section>

      <section className="py-16">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="mb-12 text-center">
            <h2 className="text-3xl font-bold text-gray-900">Built for short-term hiring</h2>
            <p className="mt-4 text-lg text-gray-600">Simple enough for V1, structured for V2 and V3 growth.</p>
          </div>

          <div className="grid grid-cols-1 gap-6 md:grid-cols-3">
            {[
              ['Fast hiring', 'Post urgent shifts, review applicants, and fill roles quickly.'],
              ['Trust controls', 'Verification, attendance, ratings, reports, and audit trails protect both sides.'],
              ['Operational dashboards', 'Workers, employers, and admins each get focused views for daily work.'],
            ].map(([title, body]) => (
              <div key={title} className="rounded-lg border border-gray-100 bg-white p-6 shadow-sm transition-shadow hover:shadow-md">
                <h3 className="mb-2 text-xl font-semibold text-gray-900">{title}</h3>
                <p className="text-gray-600">{body}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="-mx-4 bg-gray-100 py-16 sm:-mx-6 lg:-mx-8">
        <div className="mx-auto max-w-7xl px-4 text-center sm:px-6 lg:px-8">
          <h2 className="mb-10 text-3xl font-bold text-gray-900">Popular Job Categories</h2>
          <div className="flex flex-wrap justify-center gap-4">
            {['Event Helper', 'Retail Assistant', 'Delivery Rider', 'Catering Staff', 'Warehouse Picker', 'Promoter'].map((cat) => (
              <span key={cat} className="rounded-full border border-gray-200 bg-white px-6 py-3 font-medium text-gray-800 shadow-sm transition-colors hover:border-blue-500 hover:text-blue-600">
                {cat}
              </span>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
}
